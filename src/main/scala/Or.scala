import org.scalameter.api._

sealed abstract class Or[+A, +B] {
  final def monomorphic = this match {
    case Left(_) => true
    case Right(_) => false
  }

  def polymorphic: Boolean
}

case class Left[+A](a: A) extends (A Or Nothing) {
  final def polymorphic: Boolean = true
}

case class Right[+B](a: B) extends (Nothing Or B) {
  final def polymorphic: Boolean = false
}

object OrBenchmark extends PerformanceTest.Quickbenchmark {
  val sizes = Gen.exponential("size")(1, 1048576, 2)
  val ors = for { size <- sizes } yield (0 until size) map {
    case i if i % 2 == 0 => Left(0)
    case _ => Right(0)
  }

  performance of "Ok" in {
    measure method "monomorphic" in {
      using(ors) in { _.foreach(_.monomorphic) }
    }
    measure method "polymorphic" in {
      using(ors) in { _.foreach(_.polymorphic) }
    }
  }
}
