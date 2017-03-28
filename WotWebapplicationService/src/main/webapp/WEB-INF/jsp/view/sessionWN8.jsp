<div class="row session">
 <div class="col col-lg-4 col-md-4 col-sm-12 col-xs-12  text-center">
  <a
   href="
    <c:url value="session">
     <c:param name="id" value="${id}" />
     <c:param name="platform" value="${platform}" />
     <c:param name="action" value="start" />                   
    </c:url>
   "
   class="btn btn-primary btn-lg btn-horizontal">Start session</a>
 </div>
 <div class="col col-lg-4 col-md-4 col-sm-12 col-xs-12  text-center">
  <a
   href="
    <c:url value="session">
     <c:param name="id" value="${id}" />
     <c:param name="platform" value="${platform}" />
     <c:param name="action" value="get" />                   
    </c:url>
   "
   class="btn btn-primary btn-lg btn-horizontal">Get session</a>
 </div>
 <div class="col col-lg-4 col-md-4 col-sm-12 col-xs-12 text-center">
  <a
   href="
    <c:url value="session">
     <c:param name="id" value="${id}" />
     <c:param name="platform" value="${platform}" />
     <c:param name="action" value="end" />                   
    </c:url>
   "
   class="btn btn-primary btn-lg btn-horizontal">End session</a>
 </div>
</div>

<div class="row">
 <div class="container-fluid">
  <div class="panel panel-success">
   <div class="panel-heading">
    <h3 class="panel-title">Session statistics</h3>
   </div>
   <div class="panel-body">
    <c:choose>
     <c:when test="${sessionWN8 == null}">No session started yet</c:when>
     <c:otherwise> Session created</c:otherwise>
    </c:choose>
   </div>
  </div>
 </div>
</div>