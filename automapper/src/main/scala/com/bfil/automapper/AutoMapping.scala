package com.bfil.automapper

trait AutoMapping {
  protected implicit def fromMap[T: Mappable](map: Map[String, Any]) = implicitly[Mappable[T]].fromMap(map)
  protected implicit def toMap[T: Mappable](t: T) = implicitly[Mappable[T]].toMap(t)

  implicit class MappableMap(map: Map[String, Any]) {
    def as[T: Mappable]: T = fromMap[T](map)
  }

  implicit class MappableInstance[T: Mappable](t: T) {
    def asMap: Map[String, Any] = toMap[T](t)
    def mapTo[T2: Mappable] = t.asMap.as[T2]
  }
}