package com.czajor.JEP471DeprecateMemoryAccessMethods;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class UsingVarHandle {
  private static final VarHandle X_VH;

  static {
    try {
      X_VH = MethodHandles.lookup().findVarHandle(UsingVarHandle.class, "x", int.class);
    } catch (Exception ex) {
      throw new AssertionError(ex);
    }
  }

  private int x;

  public boolean tryAtomicallyDoubleX() {
    int oldValue = x;
    return X_VH.compareAndSet(this, oldValue, oldValue * 2);
  }

  public static void main(String[] args) {
    UsingVarHandle usingVarHandle = new UsingVarHandle();
    usingVarHandle.tryAtomicallyDoubleX();
  }

}
