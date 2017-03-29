<div class="row session">
 <div class="col col-lg-4 col-md-4 col-sm-12 col-xs-12  text-center">
  <a
   href="
    <c:url value="session_wn8">
     <c:param name="id" value="${playerId}" />
     <c:param name="platform" value="${platform}" />
     <c:param name="action" value="start" />                   
    </c:url>"
   class="
    btn btn-primary btn-lg btn-horizontal
    <c:if test="${sessionStarted}"> disabled</c:if>
    ">Start session</a>
 </div>
 <div class="col col-lg-4 col-md-4 col-sm-12 col-xs-12  text-center">
  <a
   href="
    <c:url value="session_wn8">
     <c:param name="id" value="${playerId}" />
     <c:param name="platform" value="${platform}" />
     <c:param name="action" value="get" />                   
    </c:url>
   "
   class="btn btn-primary btn-lg btn-horizontal
   <c:if test="${!sessionStarted}"> disabled</c:if>
   ">Get session</a>
 </div>
 <div class="col col-lg-4 col-md-4 col-sm-12 col-xs-12 text-center">
  <a
   href="
    <c:url value="session_wn8">
     <c:param name="id" value="${playerId}" />
     <c:param name="platform" value="${platform}" />
     <c:param name="action" value="end" />                   
    </c:url>
   "
   class="btn btn-primary btn-lg btn-horizontal
    <c:if test="${!sessionStarted}"> disabled</c:if>
   ">End session</a>
 </div>
</div>

<div class="row">
 <div class="container-fluid">
  <div class="panel panel-success">
   <div class="panel-heading">
    <h3 class="panel-title">Session statistics</h3>
   </div>
   <div class="panel-body">
   <div class="row">
   <div class="col col-lg-6 col-md-6 col-sm-12 col-xs-12">
    <c:choose>
     <c:when test="${sessionWN8 == null}">No session started yet</c:when>
     <c:otherwise>
      <table class="table table-striped">
       <tr>
        <td>Games played</td>
        <td>${sessionWN8.getGamesCount()}</td>
       </tr>
       <tr>
        <td>Session WN8</td>
        <td style="background-color: ${sessionWN8color}"><fmt:formatNumber type="number" maxFractionDigits="2" value="${sessionWN8.getWn8Value()}" /> (${sessionWN8rank})</td>
       </tr>
       <tr>
        <td>Session total damage</td>
        <td>${sessionWN8.getTotalDamage()}</td>
       </tr>
       <tr>
        <td>Session total frags</td>
        <td>${sessionWN8.getTotalFrags()}</td>
       </tr>
       <tr>
        <td>Games played</td>
        <td>${sessionWN8.getTotalDamage()}</td>
       </tr>
      </table>
     </c:otherwise>
    </c:choose>
    </div>
    </div>
   </div>
  </div>
 </div>
</div>