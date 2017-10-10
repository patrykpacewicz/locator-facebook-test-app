package pl.ppac.support

case class Errors(errors: List[Error])
case class Error(error: String, message: String)

object Errors {
  def from(exs: List[Exception]): Errors = Errors(exs.map(Error.from))
  def from(ex: Exception): Errors = Errors.from(List(ex))
}

object Error {
  def from(ex: Exception): Error = Error(ex.getClass.getSimpleName, ex.getMessage)
}