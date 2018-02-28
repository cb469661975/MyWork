package com.example.cheng.myapplication.fragment.model;

import java.util.HashMap;

/**
 * Created by chengbiao on 2018/2/9.
 */

public class Functions {
    private static Functions mInstance;

    public static Functions getInstance() {
        if (mInstance == null) {
            synchronized (Functions.class) {
                mInstance = new Functions();
            }
        }
        return mInstance;
    }

    public HashMap<String, NoParamNoResultFunction> functionsHashMa;

    public static class Function {
        public String functionName;

        public String getFunctionName() {
            return functionName;
        }

        public void setFunctionName(String functionName) {
            this.functionName = functionName;
        }
    }

    public abstract static class NoParamNoResultFunction extends Function {

        public NoParamNoResultFunction(String functionName) {
            this.functionName = functionName;
        }

        public abstract void invokeFunction();
    }

    public void addFunction(NoParamNoResultFunction function) throws Exception {
        if (functionsHashMa == null) {
            functionsHashMa = new HashMap<>();
        }
        if (function == null) {
            throw new Exception("no function");
        } else {
            functionsHashMa.put(function.functionName, function);
        }
    }

    public void invokeFunction(String name) {
        if (functionsHashMa != null) {
            NoParamNoResultFunction noParamNoResultFunction = functionsHashMa.get(name);
            if (noParamNoResultFunction != null) {
                noParamNoResultFunction.invokeFunction();
            }
        }
    }
}
