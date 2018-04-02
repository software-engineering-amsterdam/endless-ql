package nl.uva.se.sc.niro.qls.view.component

import nl.uva.se.sc.niro.ql.view.component.{ ComponentFactory, QLComponentFactoryBuilder }

class QLSComponentFactoryBuilder extends QLComponentFactoryBuilder {
  private var isWithFontSize = false
  private var isWithFontType = false
  private var isWithColor = false
  private var isWithWidth = false

  def buildWithFontSize(): QLSComponentFactoryBuilder = {
    isWithFontSize = true
    this
  }

  def buildWithFontType(): QLSComponentFactoryBuilder = {
    isWithFontType = true
    this
  }

  def buildWithColor(): QLSComponentFactoryBuilder = {
    isWithColor = true
    this
  }

  def buildWithWidth(): QLSComponentFactoryBuilder = {
    isWithWidth = true
    this
  }

  override def build(): ComponentFactory = {
    var componentFactory = super.build()
    if (isWithFontSize)
      componentFactory = new ComponentFactoryFontSizeDecorator(componentFactory)
    if (isWithFontType)
      componentFactory = new ComponentFactoryFontTypeDecorator(componentFactory)
    if (isWithColor)
      componentFactory = new ComponentFactoryColorDecorator(componentFactory)
    if (isWithWidth)
      componentFactory = new ComponentFactoryWidthDecorator(componentFactory)

    componentFactory
  }
}

object QLSComponentFactoryBuilder extends QLSComponentFactoryBuilder
