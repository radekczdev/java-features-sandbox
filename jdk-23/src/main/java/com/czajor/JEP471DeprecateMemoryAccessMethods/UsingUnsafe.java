package com.czajor.JEP471DeprecateMemoryAccessMethods;

import sun.misc.Unsafe;

public class UsingUnsafe {
  private static final Unsafe UNSAFE;
  private static final long X_OFFSET;

  static {
    try {
      UNSAFE = Unsafe.getUnsafe();
      X_OFFSET = UNSAFE.objectFieldOffset(UsingUnsafe.class.getDeclaredField("x"));
    } catch (Exception ex) {
      throw new AssertionError(ex);
    }
  }

  private int x;

  public static void main(String[] args) {
    UsingUnsafe usingUnsafe = new UsingUnsafe();
    usingUnsafe.tryToDoubleAtomically();
  }

  public boolean tryToDoubleAtomically() {
    int oldValue = x;
    return UNSAFE.compareAndSwapInt(this, X_OFFSET, oldValue, oldValue * 2);
  }


}

