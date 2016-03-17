package forms

import play.api.data._
import play.api.data.Forms._
import java.util.Date

case class StudentData(
  id: Int,
  name: String,
  dateOfBirth: Date,
  address: AddressData,
  grades: List[GradeData])

case class AddressData(
  street: String,
  number: Int)

case class GradeData(
  subject: String,
  grade: Int)

object StudentForm {

  val form = Form(
    mapping(
      "id" -> number(min = 1),
      "name" -> text,
      "dateOfBirth" -> date,
      "address" -> mapping(
        "street" -> text,
        "number" -> number(min = 1)
      )(AddressData.apply)(AddressData.unapply),
      "grades" -> list(mapping(
        "subject" -> text,
        "grade" -> number
      )(GradeData.apply)(GradeData.unapply))
    )(StudentData.apply)(StudentData.unapply))

}