package controllers

import javax.inject.Inject
import play.api._
import play.api.mvc._
import play.api.i18n.I18nSupport
import play.api.i18n.MessagesApi
import forms.StudentForm



class Application @Inject() (val messagesApi: MessagesApi) extends Controller with I18nSupport {

  val allTeachers = List("Sake", "Amer", "Meho")
  
  def showCreate = Action {
    Ok(views.html.create(StudentForm.form, allTeachers))
  }

  def create = Action { implicit request =>
    StudentForm.form.bindFromRequest.fold(
      formWithErrors => {
        println("ERROR")
        println(formWithErrors.data)
        // binding failure, you retrieve the form containing errors:
        BadRequest(views.html.create(formWithErrors, allTeachers))
      },
      studentData => {
        /* binding success, you get the actual value. */
        println("SUCCESS")
        println(studentData)

        Redirect(routes.Application.showCreate)
      }
    )
  }

}
