package com.czajor;

public class JEP467MarkdownDocComments {

  /**
   * Oldstyle <b>bold</b> text.
   * <p>
   * Some list:
   * <ul>
   *   <li>first</li>
   *   <li>second</li>
   * </ul>
   *
   * @return a hash code value for this object.
   * @implSpec The {@code hashCode} method defined by class {@code Object} returns distinct
   * integers for distinct objects as far as is practical.
   * @see java.lang.Object#equals(java.lang.Object)
   * @see java.lang.System#identityHashCode
   */

  /// New style **bold** text.
  /// Some list:
  /// - first
  /// - second
  /// @implSpec The `hashCode` method defined by class `Object` returns distinct
  /// integers for distinct objects as far as is practical.
  /// @return a hash code value for this object.
  /// @see     java.lang.Object#equals(java.lang.Object)
  /// @see     java.lang.System#identityHashCode
  public void someMethod() {
  }

}
