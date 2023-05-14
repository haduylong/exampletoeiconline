<div class="header">
    <div class="headertop_desc">
        <div class="call">
            <p><span>Need help?</span> call me <span class="number"><a href="#">Long.hd204841@sis.hust.edu.vn</a></span></p>
        </div>
        <div class="account_desc">
            <ul>
                <c:if test="${not empty login_name}">
                    <li>Xin chao: ${login_name}</li>
                    <c:url var="logoutUrl" value="/logout.html">
                        <c:param name="action" value="logout"/>
                    </c:url>
                    <li><a href="home.html"><u>Home</u></a></li>
                    <li><a href="${logoutUrl}"><fmt:message key="label.logout" bundle="${lang}"/></a></li>
                </c:if>
                <c:if test="${empty login_name}">
                    <c:url var="loginUrl" value="/login.html">
                        <c:param name="action" value="login"/>
                    </c:url>
                    <h5 style="color: red;"><a href="home.html"><u>Home</u></h5></a>
                    <a href="${loginUrl}"><h5 style="color: red;"><fmt:message key="label.login" bundle="${lang}"/></h5></a>
                </c:if>
            </ul>
        </div>
        <div class="clear"></div>
    </div>
</div>