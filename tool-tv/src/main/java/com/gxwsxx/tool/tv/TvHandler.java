package com.gxwsxx.tool.tv;

import com.gxwsxx.tool.tv.annotation.TvUserParameter;
import com.gxwsxx.tool.tv.datamodel.StbType;
import com.gxwsxx.tool.tv.datamodel.TvDm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 电视用户访问参数处理类
 *
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class TvHandler {

    private Logger log = LoggerFactory.getLogger(TvHandler.class);

    private static final Pattern STB_0203 =
        Pattern.compile("(Safari)|(Chrome)", Pattern.CASE_INSENSITIVE);

    /**
     * 处理request参数
     *
     * @param req http servlet request 对象
     * @return tv 相关参数
     * @throws IllegalArgumentException     非法参数异常
     * @throws IllegalAccessException       非法访问权限异常
     * @throws UnsupportedEncodingException 不支持的编码格式异常
     */
    public TvDm handleParam(HttpServletRequest req)
        throws TvException, IllegalArgumentException, IllegalAccessException,
        UnsupportedEncodingException {
        TvDm param = new TvDm();
        // 处理请求参数
        Class<TvDm> cls = TvDm.class;
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            handleField(f, param, req);
        }
        StbType type = handleStbType(param, req);
        param.setType(type.getKey());
        // 根据机顶盒类型，设置request编码方式
        if (StbType.ONE.equals(type)) {
            req.setCharacterEncoding("gb2312");
        }
        // 处理url
        String url = handleUrlParam(param);
        param.setParams(url);
        return param;
    }

    /**
     * 处理字段，通过注解
     *
     * @param f     需要处理 的字段
     * @param param web tv 相关参数对象
     * @param req   HttpServletRequest
     * @throws IllegalArgumentException 非法参数异常
     * @throws IllegalAccessException   非法访问权限异常
     * @throws TvException              电视机顶盒对象异常
     */
    public void handleField(Field f, TvDm param, HttpServletRequest req)
        throws IllegalArgumentException, IllegalAccessException, TvException {
        TvUserParameter ann = f.getAnnotation(TvUserParameter.class);
        if (null == ann) {
            return;
        }
        String v = "";
        for (String s : ann.name()) {
            if ("".equals(s)) {
                s = f.getName();
            }
            v = req.getParameter(s);
            if (null != v) {
                f.setAccessible(true);
                f.set(param, v);
                return;
            }
        }
        if ("".equals(v) && ann.require()) {
            TvException e = new TvException();
            for (String s : ann.name()) {
                e.appendName(s).appendName(",");
            }
            throw e;
        }
    }

    /**
     * 将tv相关参数处理为url参数
     *
     * @param param web tv参数对象
     * @return 返回url参数
     * @throws IllegalArgumentException 非法参数异常
     * @throws IllegalAccessException   非法访问权限异常
     * @since 1.1
     */
    public String handleUrlParam(TvDm param)
        throws IllegalArgumentException, IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        Class<TvDm> cls = TvDm.class;
        Field[] fields = cls.getDeclaredFields();
        TvUserParameter ann;
        Object value;
        for (Field f : fields) {
            ann = f.getAnnotation(TvUserParameter.class);
            if (null == ann) {
                continue;
            }
            f.setAccessible(true);
            value = f.get(param);
            if (null == value) {
                value = "";
            }
            sb.append("&");
            sb.append(getName(ann, f));
            sb.append("=");
            sb.append(value.toString());
        }
        return sb.toString();
    }

    /**
     * 处理机顶盒类型
     *
     * @param param web tv参数对象
     * @param req   HttpServletRequest对象
     * @return 机顶盒类型枚举对象
     * @throws TvException 必要的参数非法异常
     * @since 1.1
     */
    public StbType handleStbType(TvDm param, HttpServletRequest req) throws TvException {
        StbType type;
        try {
            type = StbType.fromInt(Integer.parseInt(param.getType()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            TvException te = new TvException();
            te.setStackTrace(e.getStackTrace());
            te.appendName("type 的值不能正确转换为[1,2,3]中的任意一个");
            throw te;
        }
        if (StbType.THREE.equals(type)) {
            return StbType.THREE;
        }
        String ua = req.getHeader("User-Agent");
        Matcher m2 = STB_0203.matcher(ua);
        if (m2.find()) {
            return StbType.TWO;
        } else {
            return StbType.ONE;
        }
    }

    /**
     * 获取参数名
     *
     * @param ann web tv 注解对象
     * @param f   字段
     * @return 参数名
     * @since 1.1
     */
    private String getName(TvUserParameter ann, Field f) {
        String name = ann.name()[0];
        if ("".equals(name)) {
            name = f.getName();
        }
        return name;
    }

    /**
     * 处理时间
     *
     * @return 时间,"yyyy|MM|dd|HH|mm|ss"
     */
    public TvDm handleTime(TvDm tvdm) {
        StringBuilder sb = new StringBuilder();
        LocalDateTime time = LocalDateTime.now();
        sb.append(time.getYear()).append("|").append(time.getMonthValue()).append("|")
            .append(time.getDayOfMonth()).append("|").append(time.getHour()).append("|")
            .append(time.getMinute()).append("|").append(time.getSecond());
        tvdm.setTime(sb.toString());
        return tvdm;
    }

}
