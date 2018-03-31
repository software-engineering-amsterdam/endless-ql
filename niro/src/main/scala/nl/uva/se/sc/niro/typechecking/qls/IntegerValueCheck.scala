package nl.uva.se.sc.niro.typechecking.qls

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError

trait IntegerValueCheck {

  def checkIntegerValue(name: String, value: Double, valueName: String): Option[TypeCheckError] = {
    if (value.toInt != value)
      Some(TypeCheckError(name, s"The '$valueName' value '$value' is not an integer value."))
    else None
  }
}
