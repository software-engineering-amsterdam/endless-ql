import org.scalatest.FunSuite

class ScalaTestTest extends FunSuite {
  test("Scalatest helloworld") {
    assert(ScalaTest.helloWorld == "Hello World")
  }
}
