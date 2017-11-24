package com.example.cheng.myapplication.ui;

import java.util.List;

/**
 * Created by biao.cheng on 2017/10/24.
 */

public class Test {
    /**
     * error_code : 2000
     * order_dispatched_exist :
     * order_dispatched_skill_list : [{"skill_id":0,"skill_name":"1"},{"skill_id":1,"skill_name":"2"},{"skill_id":2,"skill_name":"3"},{"skill_id":3,"skill_name":"4"},{"skill_id":4,"skill_name":"5"}]
     */

    private int error_code;
    private String order_dispatched_exist;
    private List<OrderDispatchedSkillListBean> order_dispatched_skill_list;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getOrder_dispatched_exist() {
        return order_dispatched_exist;
    }

    public void setOrder_dispatched_exist(String order_dispatched_exist) {
        this.order_dispatched_exist = order_dispatched_exist;
    }

    public List<OrderDispatchedSkillListBean> getOrder_dispatched_skill_list() {
        return order_dispatched_skill_list;
    }

    public void setOrder_dispatched_skill_list(List<OrderDispatchedSkillListBean> order_dispatched_skill_list) {
        this.order_dispatched_skill_list = order_dispatched_skill_list;
    }

    public static class OrderDispatchedSkillListBean {
        /**
         * skill_id : 0
         * skill_name : 1
         */

        private int skill_id;
        private String skill_name;

        public int getSkill_id() {
            return skill_id;
        }

        public void setSkill_id(int skill_id) {
            this.skill_id = skill_id;
        }

        public String getSkill_name() {
            return skill_name;
        }

        public void setSkill_name(String skill_name) {
            this.skill_name = skill_name;
        }
    }
}
