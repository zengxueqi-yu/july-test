package com.july.test.config;

import java.util.HashMap;

/**
 * 控制器返回结果的封装类.
 * @author cqyhm
 */
public class Result extends HashMap<String, Object> {

	private static final long serialVersionUID = -8194821450292902175L;
	/**成功状态*/
	private static final Integer SUCCESS = 0;
	/**失败状态码*/
	private static final Integer FAIL = -1;
	/**
	 * 处理成功的结果.
	 */
	public Result() {
		this("处理成功");
	}
	/**
	 * 处理成功的结果.
	 * @param msg 提示消息
	 */
	public Result(String msg) {
		put("code", SUCCESS);
		put("msg", msg);
	}
	/**
	 * 返回错误的消息
	 * @param code 错误编码
	 * @param msg 错误消息
	 * @return 错误结果
	 */
	public static  Result error(int code, String msg) {
		Result r = new Result();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}
	
	/**
	 * 返回位置错误的对象.
	 * @return 错误结果
	 */
	public static Result error() {
		return error(FAIL, "未知异常，请联系管理员");
	}
	/**
	 * 返回指定错误的指定消息的错误.
	 * @param msg 错误消息
	 * @return 错误结果
	 */
	public static  Result error(String msg) {
		return error(FAIL, msg);
	}
	/**
	 * 返回成功的消息
	 * @param msg 正确消息
	 * @return 正确结果
	 */
	public static  Result ok(String msg) {
		Result r = new Result();
		r.put("msg", msg);
		return r;
	}
	/**
	 * 成功返回结果主要包含
	 * @param data 业务数据可以是List、Entity等数据
	 * @return 正确结果
	 */
	public static Result ok(Object data) {
		Result r = new Result();
		r.put("content", data);
		return r;
	}

	/**
	 * 直接返回OK的结果
	 * @return 正确结果
	 */
	public static  Result ok() {
		return new Result();
	}

	@Override
	public Result put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}