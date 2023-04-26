package expression;

import java.util.Stack;

public interface IExpression {

  //Retourne l'identifieur du type d'expression
  String getToken();

  //Retourne l'expression sous forme de String
  String getExpression();

  //Sauvegarde la chaine de caractere dans l'expression
  void setExpression(String expression);

  //Sauvegarde l'expression dans un fichier au format XML
  void save(String fileName);
}
