<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th>Player Nickname</th>
      <th>Platform</th>
      <th>Account ID</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${playersSearch}" var="player">
	  <tr class='clickable-row' data-href='player_page?id=${player.getAccountId()}&platform=${player.getPlatform()}'>
	    <td>${player.getNickname()}</td>
	    <td>${player.getPlatform()}</td>
	    <td>${player.getAccountId()}</td>
	  </tr>
    </c:forEach>
  </tbody>
</table>
<script type="text/javascript">

jQuery(document).ready(function($) {
    $(".clickable-row").click(function() {
        window.location = $(this).data("href");
    });
});
</script>