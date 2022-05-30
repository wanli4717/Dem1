package com.company.protoType;

public class ProtoType implements Cloneable{

    public ProtoType clone() {
        ProtoType protoType = null;
        try {
            try {
                protoType = (ProtoType)super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return protoType;
    }
}
