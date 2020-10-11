<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/main.css" rel="stylesheet" type="text/css"/>
</head>
<body>



 <div class="app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header">
        <div class="app-header header-shadow">
            <div class="app-header__logo">
                <div class="logo-src"></div>
                <div class="header__pane ml-auto">
                    <div>
                        <button type="button" class="hamburger close-sidebar-btn hamburger--elastic" data-class="closed-sidebar">
                            <span class="hamburger-box">
                                <span class="hamburger-inner"></span>
                            </span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="app-header__mobile-menu">
                <div>
                    <button type="button" class="hamburger hamburger--elastic mobile-toggle-nav">
                        <span class="hamburger-box">
                            <span class="hamburger-inner"></span>
                        </span>
                    </button>
                </div>
            </div>
            <div class="app-header__menu">
                <span>
                    <button type="button" class="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav">
                        <span class="btn-icon-wrapper">
                            <i class="fa fa-ellipsis-v fa-w-6"></i>
                        </span>
                    </button>
                </span>
            </div>    <div class="app-header__content">
                
                <div class="app-header-right">
                    <div class="header-btn-lg pr-0">
                        <div class="widget-content p-0">
                            <div class="widget-content-wrapper">
                               
                                <div class="widget-content-left  ml-3 header-user-info">
                                    <div class="widget-heading">
                                        Bienvenue ADMIN
                                    </div>
                                  
                                </div>
                                
                            </div>
                        </div>
                    </div>        </div>
            </div>
        </div>       
  <div class="app-main">
                <div class="app-sidebar sidebar-shadow">
                    <div class="app-header__logo">
                        <div class="logo-src"></div>
                        <div class="header__pane ml-auto">
                            <div>
                                <button type="button" class="hamburger close-sidebar-btn hamburger--elastic" data-class="closed-sidebar">
                                    <span class="hamburger-box">
                                        <span class="hamburger-inner"></span>
                                    </span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="app-header__mobile-menu">
                        <div>
                            <button type="button" class="hamburger hamburger--elastic mobile-toggle-nav">
                                <span class="hamburger-box">
                                    <span class="hamburger-inner"></span>
                                </span>
                            </button>
                        </div>
                    </div>
                    <div class="app-header__menu">
                        <span>
                            <button type="button" class="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav">
                                <span class="btn-icon-wrapper">
                                    <i class="fa fa-ellipsis-v fa-w-6"></i>
                                </span>
                            </button>
                        </span>
                    </div>    <div class="scrollbar-sidebar">
                        <div class="app-sidebar__inner">
                            <ul class="vertical-nav-menu">
                                 <li>
								<s:url namespace="/prop" action="index" var="lancer_prop" />
								<s:a href="%{lancer_prop}" style="font-size:15px;font-weight:bold;">Gestion des proprietaires</s:a>
								</li>
								 <br />
								<li>
								<s:url namespace="/credit"  action="index" var="lancer_credit" />
								<s:a href="%{lancer_credit}" style="font-size:15px;font-weight:bold;">Gestion des cartes</s:a>
								</li>
								 <br />
								<li>
								<s:url namespace="/facture"  action="index" var="lancer_facture" />
								<s:a href="%{lancer_facture}" style="font-size:15px;font-weight:bold;">Gestion des factures</s:a>
								</li>
								 <br />
								<li>
								<s:url namespace="/transaction"  action="index" var="lancer_transaction" />
								<s:a href="%{lancer_transaction}" style="font-size:15px;font-weight:bold;">Gestion des transactions</s:a>
								</li>
                            </ul>
                        </div>
                    </div>
                </div>    <div class="app-main__outer">
                    <div class="app-main__inner">
                     
                          
                          <div class="row">
                                    <div class="col-md-12">
                                        <div class="main-card mb-3 card">
                                            <div class="card-body"><h5 class="card-title">Ajouter / Modifier Proprietaire</h5>                            
                                                <s:form namespace="/prop" action="addOrSaveProp" method="post" style="width:100%;">                                    
												<s:textfield name="idProp" label="Votre identificateur" class="form-control" style="width:100%;"/>							
												<s:textfield name="nomPrenom" label="Votre Nom & Prenom" class="form-control" style="width:100%;"/>							
												<s:submit value="Save" class="mt-1 btn btn-primary"/>
												</s:form>
                                            </div>
                                        </div>
                                    </div>
                  
                                </div>
                         <div class="row">
                         
                            <div class="col-lg-12">
                            
					          <s:form namespace="/prop" action="search" method="post" style="width:100%;"> 	         
				         	        <s:hidden name="idProp"  value="1"/>   
				         	        <s:hidden name="nomPrenom"  value="null"/>                                   
									<s:textfield name="searchTerm" class="form-control"  placeholder="nomPrenom" style="width:100%;"/>													
									<s:submit value="recherche" class="mt-1 btn btn-primary" style="width:100%;"/>
							  </s:form>
					         <br/>
                                <div class="main-card mb-3 card">
                                    <div class="card-body"><h5 class="card-title">Liste des proprietaires</h5>                             
                                        <table class="mb-0 table" >
                                        <thead>
										<tr><th>Id Proprietaire</th><th>Nom & Prenom</th><th>Actions</th></tr>
										</thead>
										<tbody>
										<s:iterator value="listeProprietaires">
										<tr><td><s:property value="id"/></td>
										<td><s:property value="nomPrenom"/></td>
										<td>
										<s:url namespace="/prop" action="updateProp" var="update_Prop" >
										<s:param name="idProp" value="%{id}"/>
										<s:param name="nomPrenom" value="%{nomPrenom}"/>
										</s:url>
										<s:a href="%{update_Prop}">Modifier</s:a>
										-
										<s:url namespace="/prop" action="deleteProp" var="delete_Prop" >
										<s:param name="idProp" value="%{id}"/>
										<s:param name="nomPrenom" value="%{nomPrenom}"/>
										</s:url>
										<s:a href="%{delete_Prop}">Supprimer</s:a>
										</td>
										
										</tr>
										</s:iterator>
										</tbody>
										</table>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="app-wrapper-footer">
                        <div class="app-footer">
                            <div class="app-footer__inner">
                                
                                <div class="app-footer-right">
                                 Realise par Mustapha Baimik,Oumeima el Yazaji et Anouar El Bardaoui
                                </div>
                            </div>
                        </div>
                    </div>    </div>
        </div>
    </div>






<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body>
</html>