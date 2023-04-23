package expression;

public interface IExpression {

  //Retourne l'identifieur du type d'expression
  public String getToken();

  //Sauvegarde l'expression dans un fichier au format XML
  public void save(String fileName);
}
