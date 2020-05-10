package catalina;

public class FirstServlet extends MyServlet {

    @Override
    void doGet(MyRequest request, MyResponse response) {
        doPost(request, response);
    }

    @Override
    void doPost(MyRequest request, MyResponse response) {
        String param = "name";
        String paramter = request.getParamter(param);
        response.write(param + ":" + paramter, 200);
    }
}
