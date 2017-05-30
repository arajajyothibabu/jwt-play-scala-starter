package controllers

import models.UserLoginRequestModel
import play.api._
import play.api.libs.json.JsValue
import play.api.mvc._
import services.AuthService

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class Application extends Controller {

  val authService = new AuthService()

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def login = Action.async(parse.json) { implicit request =>
    request.body.validate[UserLoginRequestModel].fold(
      _ => Future(BadRequest("Bad Request Buddy..!")),
      validModel => {
        val authenticated = authService.login(validModel) // (Status, Token)
        if(authenticated._1){
          Future(
            Ok("Authenticated").withHeaders(
              "WWW-Authenticate" -> authenticated._2
            )
          )
        }else{
          Future(NoContent)
        }
      }
    )
  }

  def securedAction = AuthenticatedAction.async { request =>
    Future(Ok(s"You are legitimate Mr. ${request}..!"))
  }

}