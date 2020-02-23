package com.july.test.jenum;

/**
 * 性别信息
 * @author zqk
 * @since 2019/11/27
 */
public enum SexEnum {

    /**
     * 女
     */
    WOMAN(0, "女"),

    /**
     * 男
     */
    MAN(1, "男");

    private Integer value;
    private String desc;

    SexEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "" + value;
    }

	public String getDesc() {
		return desc;
	}

}
