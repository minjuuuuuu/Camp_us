<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><decorator:title default="Kim's System"/></title>
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/jsgrid/jsgrid.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/jsgrid/jsgrid-theme.min.css">
<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:300,400,700&display=swap">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Tempusdominus Bootstrap 4 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/bootstrap/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<!-- iCheck -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/bootstrap/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- JQVMap -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/bootstrap/plugins/jqvmap/jqvmap.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/bootstrap/dist/css/adminlte.min.css">
<!-- overlayScrollbars -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/bootstrap/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
<!-- Daterange picker -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/bootstrap/plugins/daterangepicker/daterangepicker.css">
<!-- summernote -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/plugins/summernote/summernote-bs4.min.css">

<style>
body {
  font-family: 'Noto Sans KR', sans-serif;
}
i{}
.custom-img-icon {
  display: inline-block;
  width: 18px;   /* 이미지 크기 조절 */
  height: 18px;
  background-image: url('<%=request.getContextPath() %>/resources/bootstrap/dist/img/CPhome.png');
  background-size: contain;
  background-repeat: no-repeat;
  transition: background-image 0.3s;
}

.nav-link:hover .custom-img-icon {
  background-image: url('<%=request.getContextPath() %>/resources/bootstrap/dist/img/home_hv.png');
}
.gang-img-icon {
  display: inline-block;
  width: 18px;   /* 이미지 크기 조절 */
  height: 18px;
  background-image: url('<%=request.getContextPath() %>/resources/bootstrap/dist/img/gang.png');
  background-size: contain;
  background-repeat: no-repeat;
  transition: background-image 0.3s;
}

.nav-link:hover .gang-img-icon {
  background-image: url('<%=request.getContextPath() %>/resources/bootstrap/dist/img/gang_hv.png');
}
.pro-img-icon {
  display: inline-block;
  width: 18px;   /* 이미지 크기 조절 */
  height: 18px;
  background-image: url('<%=request.getContextPath() %>/resources/bootstrap/dist/img/pro.png');
  background-size: contain;
  background-repeat: no-repeat;
  transition: background-image 0.3s;
}

.nav-link:hover .pro-img-icon {
  background-image: url('<%=request.getContextPath() %>/resources/bootstrap/dist/img/pro_hv.png');
}
.post-img-icon {
  display: inline-block;
  width: 18px;   /* 이미지 크기 조절 */
  height: 18px;
  background-image: url('<%=request.getContextPath() %>/resources/bootstrap/dist/img/post.png');
  background-size: contain;
  background-repeat: no-repeat;
  transition: background-image 0.3s;
}

.nav-link:hover .post-img-icon {
  background-image: url('<%=request.getContextPath() %>/resources/bootstrap/dist/img/post_hv.png');
}
.cal-img-icon {
  display: inline-block;
  width: 18px;   /* 이미지 크기 조절 */
  height: 18px;
  background-image: url('<%=request.getContextPath() %>/resources/bootstrap/dist/img/cal.png');
  background-size: contain;
  background-repeat: no-repeat;
  transition: background-image 0.3s;
}

.nav-link:hover .cal-img-icon {
  background-image: url('<%=request.getContextPath() %>/resources/bootstrap/dist/img/cal_hv.png');
}
.mes-img-icon {
  display: inline-block;
  width: 18px;   /* 이미지 크기 조절 */
  height: 18px;
  background-image: url('<%=request.getContextPath() %>/resources/bootstrap/dist/img/mes.png');
  background-size: contain;
  background-repeat: no-repeat;
  transition: background-image 0.3s;
}

.nav-link:hover .mes-img-icon {
  background-image: url('<%=request.getContextPath() %>/resources/bootstrap/dist/img/mes_hv.png');
}
.key-img-icon {
  display: inline-block;
  width: 18px;   /* 이미지 크기 조절 */
  height: 18px;
  background-image: url('<%=request.getContextPath() %>/resources/bootstrap/dist/img/key.png');
  background-size: contain;
  background-repeat: no-repeat;
  transition: background-image 0.3s;
}

.nav-link:hover .key-img-icon {
  background-image: url('<%=request.getContextPath() %>/resources/bootstrap/dist/img/key_hv.png');
}
.log-img-icon {
  display: inline-block;
  width: 18px;   /* 이미지 크기 조절 */
  height: 18px;
  background-image: url('<%=request.getContextPath() %>/resources/bootstrap/dist/img/log.png');
  background-size: contain;
  background-repeat: no-repeat;
  transition: background-image 0.3s;
}

.nav-link:hover .log-img-icon {
  background-image: url('<%=request.getContextPath() %>/resources/bootstrap/dist/img/log_hv.png');
}
.poli-img-icon {
  display: inline-block;
  width: 18px;   /* 이미지 크기 조절 */
  height: 18px;
  background-image: url('<%=request.getContextPath() %>/resources/bootstrap/dist/img/poli.png');
  background-size: contain;
  background-repeat: no-repeat;
  transition: background-image 0.3s;
}

.nav-link:hover .poli-img-icon {
  background-image: url('<%=request.getContextPath() %>/resources/bootstrap/dist/img/poli_hv.png');
}
.psad-img-icon {
  display: inline-block;
  width: 18px;   /* 이미지 크기 조절 */
  height: 18px;
  background-image: url('<%=request.getContextPath() %>/resources/bootstrap/dist/img/psad.png');
  background-size: contain;
  background-repeat: no-repeat;
  transition: background-image 0.3s;
}

.nav-link:hover .psad-img-icon {
  background-image: url('<%=request.getContextPath() %>/resources/bootstrap/dist/img/psad_hv.png');
}
*{
	color:#212121;
}
.nav-link:hover p {
  color: #2ec4b6;
  font-weight: bold;
}
.nav-link:hover .fa-angle-left {
  color: #2ec4b6;  /* 원하는 색상으로 변경 */
}
.brand-link {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100px;          /* 로고 영역 높이 제한 */
  padding: 0;
  overflow: hidden;
}

.brand-image {
  height: 400px;          /* 이미지 실제 크기 제한 */
  width: auto;           /* 비율 유지 */
  display: block;
  object-fit: cover;   /* 혹시 필요할 경우 */
}
.custom-logo {
  max-height: none !important;
  height: 60px; /* 원하는 크기 */
}
  .custom-select.my-border {
    border: 2px solid #2ec4b6;
  }

  .custom-select.my-border:focus {
    border-color: #2ec4b6;
  }
  .sidebar-mini.sidebar-collapse .main-sidebar .nav-link p {
  display: none !important;
}
.custom-icon-message {
  display: inline-block;
  width: 30px;
  height: 20px;
  background-image: url('<%=request.getContextPath() %>/resources/bootstrap/dist/img/mail.png'); /* 이미지 경로 */
  background-size: 100% 100%;
  vertical-align: middle;
}
.main-sidebar {
  overflow-x: hidden;
  box-sizing: border-box; /* 혹은 필요에 따라 유지 */
}
.far.fa-minical {
  background-image: url('<%=request.getContextPath() %>/resources/bootstrap/dist/img/minical.png'); /* 이미지 경로 */
  background-size: contain;
  background-repeat: no-repeat;
  display: inline-block;
  width: 22px;
  height: 15px;
    vertical-align: middle;
  background-position: center center;
}
.fas.fa-cpsearch {
  background-image: url('<%=request.getContextPath() %>/resources/bootstrap/dist/img/search.png'); /* 이미지 경로 */
  background-size: contain;
  background-repeat: no-repeat;
  display: inline-block;
  width: 80px;
  height: 30px;
    vertical-align: middle;
  background-position: center center;
}
.custom-btn {
  border: 1px solid #2ec4b6;
  color: #2ec4b6;
  font-weight: bold;
  font-size: 16px;
  background-color: transparent;
  transition: all 0.2s ease;
  border-radius: 0px;
}

.custom-btn:hover {
  background-color: #2ec4b6;
  color: #ffffff;
}
</style>
<script src="<%=request.getContextPath() %>/resources/js/common.js"></script>
<decorator:head />
</head>
<body class="hold-transition sidebar-mini layout-fixed">

