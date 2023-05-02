package model.expression;

public interface IExpression {

  //Retourne l'identifieur du type d'model.expression
  String getToken();

  //Retourne l'model.expression sous forme de String
  String getExpression();

  //Sauvegarde la chaine de caractere dans l'model.expression
  void setExpression(String expression);

  //Sauvegarde l'model.expression dans un fichier au format XML
  void save(String fileName);
}
