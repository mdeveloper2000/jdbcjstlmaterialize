<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Edit</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
    </head>
    
    <body>
        
        <div class="container">
            <jsp:include page="navbar.jsp"></jsp:include>
            
            <div class="row">
                <div class="col l3"></div>
                <div class="col l6">
                    <form method="post" action="SoftwareController?query=edit">                    
                        <div class="row">
                            <div class="input-field">
                                <i class="material-icons prefix">key</i>
                                <input name="id" type="text" class="validate" readonly value="<c:out value="${software.id}" />"
                            </div>
                            <div class="input-field">
                                <i class="material-icons prefix">account_circle</i>
                                <input name="nome" type="text" class="validate" placeholder="Nome" maxlength="50" required onblur="this.value=this.value.trim().toUpperCase();" value="${software.nome}" />
                            </div>
                            <div class="input-field">
                                <i class="material-icons prefix">looks_one</i>
                                <input name="versao" type="text" class="validate" placeholder="Versão" maxlength="20" required onblur="this.value=this.value.trim().toUpperCase();" value="${software.versao}" />
                            </div>
                            <div class="input-field">
                                <i class="material-icons prefix">date_ranger</i>
                                <fmt:formatDate var="data" value="${software.data_lancamento}" type="date" pattern="dd/MM/yyyy" />
                                <input type="text" name="data_lancamento" class="datepicker" required onblur="this.value=this.value.trim();" 
                                       onkeypress="return false;" value="${data}" />
                                       
                            </div>
                            <button class="btn waves-effect waves-light right yellow" type="submit">
                                Editar <i class="material-icons right">edit</i>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            
            <script>
                document.addEventListener('DOMContentLoaded', function() {
                    var months = ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'];
                    var monthsShort = ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'];                
                    var weekdaysShort = ['Dom','Seg','Ter','Qua','Qui','Sex','Sab'];
                      
                    var options = {
                        'format':'dd/mm/yyyy',
                        'autoClose':true,
                        i18n: {
                            cancel:'Cancelar',
                            months: months,
                            monthsShort: monthsShort,
                            weekdaysShort: weekdaysShort
                        }                    
                    };
                    var elems = document.querySelectorAll('.datepicker');
                    var instances = M.Datepicker.init(elems, options);
                });
            </script>
        
    </body>
    
</html>