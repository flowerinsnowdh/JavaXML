package config;

import online.flowerinsnow.javaxml.api.annotation.ConfigValue;
import online.flowerinsnow.javaxml.api.annotation.DefaultValue;
import online.flowerinsnow.javaxml.api.annotation.MissDo;
import online.flowerinsnow.javaxml.api.annotation.OnMiss;

public class ConfigObject {
    @ConfigValue
    public FieldA fieldA;

    public static class FieldA {
        @ConfigValue
        public String fieldB;
        @ConfigValue
        public boolean fieldBoolean;
        @ConfigValue
        @OnMiss(MissDo.DEFAULT)
        @DefaultValue("666")
        public int fieldD;

        @Override
        public String toString() {
            return "FieldA{" +
                    "fieldB='" + fieldB + '\'' +
                    ", fieldBoolean=" + fieldBoolean +
                    ", fieldD=" + fieldD +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ConfigObject{" +
                "fieldA=" + fieldA +
                '}';
    }
}
