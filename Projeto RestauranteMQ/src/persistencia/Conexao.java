package persistencia;

import java.sql.*;

public abstract class Conexao {
    protected String     host;     // servidor
    protected String     user;  // nome de login
    protected String     password; // senha
    protected String     dbname;   // nome do banco
    protected Connection con;      // conexao com o banco
        
    protected static Conexao conexaoDefault = null;
    
    /** 
     * @return a conexao default
     */
    public static Conexao getConexao() {
        return conexaoDefault;        
    }
        
    /**
     * Construtor da classe 
     * @param host Nome do servidor
     * @param logname Identificacao de usuario no banco de dados
     * @param password Senha do usuario
     * @param dbname Nome do banco de dados 
     */
    public Conexao(String host, String logname, String password, String dbname){
        this.host     = host;
        this.user  = logname;
        this.password = password;
        this.dbname   = dbname;
    }
   
    /**
     * Construtor da classe 
     * @param host Nome do servidor
     * @param logname Identificacao de usuario no banco de dados
     * @param password Senha do usuario
     * @param dbname Nome do banco de dados 
     * @param atual Deve ser true se este jah for assumido como o objeto de conexao atual
     */
    public Conexao(String host, String logname, String password, String dbname, boolean atual){
        this.host     = host;
        this.user  = logname;
        this.password = password;
        this.dbname   = dbname;
        // Apos a conexao, torna-la default se atual == true
    }

    /**
     * Tenta fechar a conexao com o banco de dados
     */
    public abstract void fechar();

    
    /**
     * Define que o objeto corresponde a conexao atual
     */
    public void setDefault() {
        conexaoDefault = this;
    }
    
    /** 
     * @return retorna true se este objeto eh a conexao atual
     */
    public boolean isDefault() {
        return (this == conexaoDefault);
    }

    
    
    /**
     * @return Retorna o nome do banco de dados.
     */
    public String getBD() {
        return dbname;
    }
    
    /**
     * @return Retorna o servidor da conexao.
     */
    public String getHost() {
        return host;
    }
    
    /**
     * @return Retorna o nome do usuario.
     */
    public String getLogname() {
        return user;
    }
    
    /**
     * @return Retorna a senha do usuario.
     */
    public String getPassword() {
        return password;
    }
    
 
    
    /**
     * @return A conexao obtida ao instanciar o objeto de conexao 
     */
    public Connection getConnection() {
        return con;
    }

    
    
    /**
     * @return true se a conexao faz commit a cada query executada
     * @throws Exception
     */
    public boolean isAutoCommit() throws Exception{
        return con.getAutoCommit();
    }
 
    /**
     * @param st - true se o comportamento da conexao deve ser de autocommit, false do contrario
     * @throws Exception
     */
    public void setAutoCommit(boolean st) throws Exception{
        con.setAutoCommit(st);
    }
   
    /**
     * Define commit automatico para cada operacao do SGBD
     * @throws Exception
     */
    public void autoCommit() throws Exception{
        con.setAutoCommit(true);
    }
 
    /**
     * Define comportamento transacional, onde o commit deve ser feito pelo programador
     * @throws Exception
     */
    public void manualCommit() throws Exception {
        con.setAutoCommit(false);
    }
    
    /**
     * Encapsula o commit da conexao
     * @throws Exception
     */
    public void commit() throws Exception {
        con.commit();
    }
    
    /**
     * encapsula o rollback da conexao
     * @throws Exception
     */
    public void rollback() throws Exception {
        con.rollback();
    }
    
    /**
     * encapsula o rollback para um savepoint da conexao
     * @param sp o savepoint para onde deve-se fazer o rollback
     * @throws Exception
     */
    public void rollback(Savepoint sp) throws Exception {
        con.rollback(sp);
    }
}
