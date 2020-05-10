package catalina;


public abstract class MyServlet {

    abstract void doGet(MyRequest request, MyResponse response);

    abstract void doPost(MyRequest request, MyResponse response);

}
