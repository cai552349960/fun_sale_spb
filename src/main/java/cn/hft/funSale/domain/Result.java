package cn.hft.funSale.domain;

import java.io.Serializable;

public class Result implements Serializable {
    private Integer codeTape;
    private String message;

    public Result() {
    }

    public Result(Integer codeTape, String message) {
        this.codeTape = codeTape;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result{" +
                "codeTape=" + codeTape +
                ", message='" + message + '\'' +
                '}';
    }

    public Integer getCodeTape() {
        return codeTape;
    }

    public void setCodeTape(Integer codeTape) {
        this.codeTape = codeTape;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

