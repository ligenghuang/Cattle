package com.zhifeng.cattle.modules;

public class AgreementDto {

    /**
     * status : 200
     * msg : 获取成功
     * data : {"consult":"<p>dsfasdafsadfsadf<\/p><p>dsfasdafsadfsadf<\/p><p>dsfasdafsadfsadf<\/p><p>dsfasdafsadfsadf<\/p><p>dsfasdafsadfsadf<\/p>"}
     */

    private int status;
    private String msg;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * consult : <p>dsfasdafsadfsadf</p><p>dsfasdafsadfsadf</p><p>dsfasdafsadfsadf</p><p>dsfasdafsadfsadf</p><p>dsfasdafsadfsadf</p>
         */

        private String consult;

        public String getConsult() {
            return consult == null ? "" : consult;
        }

        public void setConsult(String consult) {
            this.consult = consult == null ? "" : consult;
        }
    }
}
