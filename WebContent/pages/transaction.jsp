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
                                            <div class="card-body"><h5 class="card-title">Ajouter / Modifier Transaction</h5>                            
                                             <s:form method="post" action="addOrSaveTransaction" namespace="/transaction" style="width:100%;">
											<s:hidden name="id" />
											<s:select list=""
													  label="Choisir la facture"
													  list="listeFacture"
													  name="FactureSelected"
													  headerKey="-1"
													  headerValue="selectionnez---"
													  listKey="numFacture"
													  listValue="numFacture"
													  class="form-control"
													  style="width:100%;"
											/>
											<s:select list=""
													  label="Choisir la carte"
													  list="listeCarte"
													  name="CarteSelected"
													  headerKey="-1"
													  headerValue="selectionnez---"
													  listKey="numCarte"
													  listValue="numCarte"
													  class="form-control"
													  style="width:100%;"
											/>
											
											<s:textfield label="Saisissez votre montant" name="montant" class="form-control" style="width:100%;"/>
											<s:submit value="Save" class="mt-1 btn btn-primary"/>
											</s:form>
                                            </div>
                                        </div>
                                    </div>
                  
                                </div>
                         <div class="row">
                            <div class="col-lg-12">
                              <div class="row">
                                 <div class="col-lg-6">
                                    <s:form namespace="/transaction" action="searchbyFac" method="post" style="width:100%;"> 	         			         	                                        
									<s:textfield name="searchTerm" class="form-control"  placeholder="numero facture" style="width:100%;"/>													
									<s:submit value="recherche" class="mt-1 btn btn-primary" style="width:100%;"/>
							        </s:form>
                                 </div> 
                                  <div class="col-lg-6">
                                    <s:form namespace="/transaction" action="searchbyCarte" method="post" style="width:100%;"> 	         			         	                                        
									<s:textfield name="searchTerm" class="form-control"  placeholder="numero Carte" style="width:100%;"/>													
									<s:submit value="recherche" class="mt-1 btn btn-primary" style="width:100%;"/>
							        </s:form>
                                 </div>            
                              </div>
                                <div class="main-card mb-3 card">
                                    <div class="card-body"><h5 class="card-title">Liste des Transactions</h5>                              
										<table class="mb-0 table">
											<tr>
												<th>N° transaction</th>
												<th>montant transaction</th>
												<th>date Paiement</th>
												<th>num carte</th>
												<th>proprietaire carte</th>
												<th>num facture</th>
												<th>montant facture</th>
												<th>date facture</th>
												<th>Actions</th>
											</tr>
											<s:iterator value="listeTransactions" var="tr">
												<tr>
												    <td>
													<s:property value="#tr[0]"/>
													</td>
													<td>
													<s:property value="#tr[1]"/>
													</td>
													<td>
													<s:property value="#tr[2]"/>
													</td>
													<td>
													<s:property value="#tr[3]"/>
													</td>
													<td>
													<s:property value="#tr[4]"/>
													</td>
													<td>
													<s:property value="#tr[5]"/>
													</td>
													<td>
													<s:property value="#tr[6]"/>
													</td>
													<td>
													<s:property value="#tr[7]"/>
													</td>
													<td>
													<s:url namespace="/transaction" action="updateTransaction" var="update_Transaction" >
													<s:param name="id" value="#tr[0]"/>
													<s:param name="CarteSelected" value="#tr[4]"/>
													<s:param name="FactureSelected" value="#cp[5]"/>
													<s:param name="montant" value="#tr[1]"/>			
													</s:url>
													<s:a href="%{update_Transaction}">Modifier</s:a>
													-
													<s:url namespace="/transaction" action="deleteTransaction" var="delete_Transaction" >
													<s:param name="id" value="#tr[0]"/>
													<s:param name="montant" value="#tr[1]"/>
													<s:param name="CarteSelected" value="#tr[4]"/>
													<s:param name="FactureSelected" value="#cp[5]"/>
													</s:url>
													<s:a href="%{delete_Transaction}">Supprimer</s:a>
													</td>
												</tr>
										</s:iterator>
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
                                   Réalisé par Mustapha Baimik,Oumeima el Yazaji et Anouar El Bardaoui
                                </div>
                            </div>
                        </div>
                    </div>    </div>
        </div>
 </div>


<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/main.js"></script>

</body>
</html>