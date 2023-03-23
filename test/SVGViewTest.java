import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import cs3500.animator.io.AnimationFileReader;
import cs3500.animator.io.ModelBuilder;
import cs3500.animator.io.TweenModelBuilder;
import cs3500.animator.model.Canvas;
import cs3500.animator.model.ICanvas;
import cs3500.animator.view.SVGView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SVGViewTest {

  TweenModelBuilder<ICanvas> tweenModelBuilder;
  AnimationFileReader animationFileReader;
  SVGView svgView;
  ICanvas canvas;
  Appendable appendable;


  @Before
  public void init() {

    tweenModelBuilder = new ModelBuilder();
    animationFileReader = new AnimationFileReader();
    canvas = new Canvas(500,500);
    appendable = System.out;
    svgView = new SVGView(canvas,appendable);

  }

  @Test
  public void renderAnimationTest() {
    try {
      StringBuilder stringBuilder = new StringBuilder();
      StringWriter stringWriter = new StringWriter();
      ICanvas canvas = animationFileReader.readFile("gnomesort.txt", tweenModelBuilder);
      svgView = new SVGView(canvas, stringWriter);
      svgView.renderAnimation();




      assertEquals( stringWriter.toString(), "<?xml version=\"1.0\" standalone=\"no\"?>\n" +
              "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \n" +
              "  \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n" +
              "<svg width=\"700\" height=\"700\" viewBox=\"0 0 700 700\" xmlns=\"" +
              "http://www.w3.org/2000/svg\" xmlns:xlink= \"http://www.w3.org/1999/xlink\">\n" +
              "<ellipse id=\"8\" cx=\"450.0\" cy=\"350.0\" rx=\"8.0\" ry=\"8.0\" fill=\"rgb" +
              "(224,58,105)\" visibility=\"visible\"  >\n" +
              "\t<animate attributeName=\"cx\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"32s\" dur=\"3\" fill=\"freeze\" from=\"450.0\" to=\"350.0\"  />\n" +
              "\t<animate attributeName=\"cy\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"32s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"350.0\"  />\n" +
              "\t<animate attributeName=\"cx\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"36s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"250.0\"  />\n" +
              "\t<animate attributeName=\"cy\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"36s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"350.0\"  />\n" +
              "\t<animate attributeName=\"cx\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"40s\" dur=\"3\" fill=\"freeze\" from=\"250.0\" to=\"150.0\"  />\n" +
              "\t<animate attributeName=\"cy\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"40s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"350.0\"  />\n" +
              "\t<animate attributeName=\"cx\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"44s\" dur=\"3\" fill=\"freeze\" from=\"150.0\" to=\"50.0\"  />\n" +
              "\t<animate attributeName=\"cy\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"44s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"350.0\"  />\n" +
              "</ellipse>\n" +
              "<ellipse id=\"19\" cx=\"250.0\" cy=\"350.0\" rx=\"19.0\" ry=\"19.0\" fill=\"rgb" +
              "(224,111,58)\" visibility=\"visible\"  >\n" +
              "\t<animate attributeName=\"cx\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"10s\" dur=\"3\" fill=\"freeze\" from=\"250.0\" to=\"150.0\"  />\n" +
              "\t<animate attributeName=\"cy\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"10s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"350.0\"  />\n" +
              "\t<animate attributeName=\"cx\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"14s\" dur=\"3\" fill=\"freeze\" from=\"150.0\" to=\"50.0\"  />\n" +
              "\t<animate attributeName=\"cy\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"14s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"350.0\"  />\n" +
              "\t<animate attributeName=\"cx\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"44s\" dur=\"3\" fill=\"freeze\" from=\"50.0\" to=\"150.0\"  />\n" +
              "\t<animate attributeName=\"cy\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"44s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"350.0\"  />\n" +
              "</ellipse>\n" +
              "<ellipse id=\"33\" cx=\"150.0\" cy=\"350.0\" rx=\"33.0\" ry=\"33.0\" fill=\"rgb" +
              "(75,201,77)\" visibility=\"visible\"  >\n" +
              "\t<animate attributeName=\"cx\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"2s\" dur=\"3\" fill=\"freeze\" from=\"150.0\" to=\"50.0\"  />\n" +
              "\t<animate attributeName=\"cy\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"2s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"350.0\"  />\n" +
              "\t<animate attributeName=\"cx\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"14s\" dur=\"3\" fill=\"freeze\" from=\"50.0\" to=\"150.0\"  />\n" +
              "\t<animate attributeName=\"cy\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"14s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"350.0\"  />\n" +
              "\t<animate attributeName=\"cx\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"40s\" dur=\"3\" fill=\"freeze\" from=\"150.0\" to=\"250.0\"  />\n" +
              "\t<animate attributeName=\"cy\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"40s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"350.0\"  />\n" +
              "\t<animate attributeName=\"cx\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"72s\" dur=\"3\" fill=\"freeze\" from=\"250.0\" to=\"350.0\"  />\n" +
              "\t<animate attributeName=\"cy\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"72s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"350.0\"  />\n" +
              "</ellipse>\n" +
              "<ellipse id=\"52\" cx=\"50.0\" cy=\"350.0\" rx=\"52.0\" ry=\"52.0\" fill=\"rgb" +
              "(96,91,245)\" visibility=\"visible\"  >\n" +
              "\t<animate attributeName=\"cx\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"2s\" dur=\"3\" fill=\"freeze\" from=\"50.0\" to=\"150.0\"  />\n" +
              "\t<animate attributeName=\"cy\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"2s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"350.0\"  />\n" +
              "\t<animate attributeName=\"cx\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"10s\" dur=\"3\" fill=\"freeze\" from=\"150.0\" to=\"250.0\"  />\n" +
              "\t<animate attributeName=\"cy\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"10s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"350.0\"  />\n" +
              "\t<animate attributeName=\"cx\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"24s\" dur=\"3\" fill=\"freeze\" from=\"250.0\" to=\"350.0\"  />\n" +
              "\t<animate attributeName=\"cy\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"24s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"350.0\"  />\n" +
              "\t<animate attributeName=\"cx\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"32s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"450.0\"  />\n" +
              "\t<animate attributeName=\"cy\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"32s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"350.0\"  />\n" +
              "\t<animate attributeName=\"cx\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"64s\" dur=\"3\" fill=\"freeze\" from=\"450.0\" to=\"550.0\"  />\n" +
              "\t<animate attributeName=\"cy\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"64s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"350.0\"  />\n" +
              "</ellipse>\n" +
              "<ellipse id=\"41\" cx=\"350.0\" cy=\"350.0\" rx=\"41.0\" ry=\"41.0\" fill=\"rgb" +
              "(91,203,240)\" visibility=\"visible\"  >\n" +
              "\t<animate attributeName=\"cx\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"24s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"250.0\"  />\n" +
              "\t<animate attributeName=\"cy\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"24s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"350.0\"  />\n" +
              "\t<animate attributeName=\"cx\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"36s\" dur=\"3\" fill=\"freeze\" from=\"250.0\" to=\"350.0\"  />\n" +
              "\t<animate attributeName=\"cy\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"36s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"350.0\"  />\n" +
              "\t<animate attributeName=\"cx\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"68s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"450.0\"  />\n" +
              "\t<animate attributeName=\"cy\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"68s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"350.0\"  />\n" +
              "</ellipse>\n" +
              "<ellipse id=\"66\" cx=\"550.0\" cy=\"350.0\" rx=\"66.0\" ry=\"66.0\" fill=\"rgb" +
              "(189,117,240)\" visibility=\"visible\"  >\n" +
              "\t<animate attributeName=\"cx\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"60s\" dur=\"3\" fill=\"freeze\" from=\"550.0\" to=\"650.0\"  />\n" +
              "\t<animate attributeName=\"cy\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"60s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"350.0\"  />\n" +
              "</ellipse>\n" +
              "<ellipse id=\"24\" cx=\"650.0\" cy=\"350.0\" rx=\"24.0\" ry=\"24.0\" fill=\"rgb" +
              "(235,215,42)\" visibility=\"visible\"  >\n" +
              "\t<animate attributeName=\"cx\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"60s\" dur=\"3\" fill=\"freeze\" from=\"650.0\" to=\"550.0\"  />\n" +
              "\t<animate attributeName=\"cy\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"60s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"350.0\"  />\n" +
              "\t<animate attributeName=\"cx\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"64s\" dur=\"3\" fill=\"freeze\" from=\"550.0\" to=\"450.0\"  />\n" +
              "\t<animate attributeName=\"cy\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"64s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"350.0\"  />\n" +
              "\t<animate attributeName=\"cx\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"68s\" dur=\"3\" fill=\"freeze\" from=\"450.0\" to=\"350.0\"  />\n" +
              "\t<animate attributeName=\"cy\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"68s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"350.0\"  />\n" +
              "\t<animate attributeName=\"cx\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"72s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"250.0\"  />\n" +
              "\t<animate attributeName=\"cy\" attributeType=\"XML\"\n" +
              "\t\tbegin=\"72s\" dur=\"3\" fill=\"freeze\" from=\"350.0\" to=\"350.0\"  />\n" +
              "</ellipse>\n" +
              "\n" +
              "</svg>");

    }catch (IOException e){
      fail();

    }





  }


}
