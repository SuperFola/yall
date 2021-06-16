package io.github.superfola

import cats.effect.{ExitCode, IO, IOApp}

object App extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    if (args.isEmpty) {
      IO(println("Not enough arguments")) *> IO.pure(ExitCode.Error)
    } else {
      IO.pure(ExitCode.Success)
    }
}
