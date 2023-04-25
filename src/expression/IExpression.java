package expression;

public interface IExpression {

  //Retourne l'identifieur du type d'expression
  String getToken();

  void buildTree(String exp);

  //Sauvegarde l'expression dans un fichier au format XML
  void save(String fileName);

  String getString();
}
