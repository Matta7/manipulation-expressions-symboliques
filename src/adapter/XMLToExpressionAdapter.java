package adapter;

import expression.IExpression;
import java.io.File;

import org.xml.sax.XMLReader;

public class XMLToExpressionAdapter implements IExpression {

  private String token;
  
  public XMLToExpressionAdapter(File file) {
    
  }

  public String getToken() {
    return token;
  }

  public void save(String fileName) {
  }
}
