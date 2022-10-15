<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP - Index</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    </head>
    
    <body>
        
        <div class="container">
            <jsp:include page="navbar.jsp"></jsp:include>
            <a class="waves-effect waves-light btn right" href="SoftwareController?query=new">
                <i class="material-icons right">add</i>Novo Software
            </a>
            <table class="highlight">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Versão</th>
                        <th>Data de lançamento</th>
                        <th>Editar</th>
                        <th>Excluir</th>
                    </tr>                    
                </thead>
                <tbody>
                <c:forEach var="software" items="${requestScope.softwares}">
                    <tr>
                        <td><c:out value="${software.id}" /></td>
                        <td style="word-break: break-all; width: 30%;"><c:out value="${software.nome}" /></td>
                        <td><c:out value="${software.versao}" /></td>
                        <td>
                            <fmt:formatDate value="${software.data_lancamento}" type="date" pattern="dd/MM/yyyy"/>
                        </td>
                        <td>
                            <a class="waves-effect waves-light btn-small yellow" 
                                href="SoftwareController?query=read&id=<c:out value="${software.id}" />">
                                <i class="material-icons right">edit</i>Editar
                            </a>                            
                        </td>
                        <td>
                            <a class="waves-effect waves-light btn-small red" 
                                href="SoftwareController?query=delete&id=<c:out value="${software.id}" />">
                                <i class="material-icons right">delete</i>Deletar
                            </a>                            
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        
    </body>
    
</html>