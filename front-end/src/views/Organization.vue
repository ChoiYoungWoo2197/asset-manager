<template>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h4 class="m-0"> 조직도 정보관리 <small></small></h4>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">기초정보</a></li>
              <li class="breadcrumb-item active">조직도 정보관리</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <div class="content">
      <div class="container">
        <div class="mb-2">
          <div class="text-right">
            <div>
              <button class="create btn btn-secondary btn-sm mr-1">
                <i class="far fa-plus-square pr-1"></i>등록
              </button>
              <!--                            <button class="update btn btn-secondary btn-sm">
                                              <i class="far fa-edit pr-1"></i>수정
                                          </button>
              <button class="remove btn btn-secondary btn-sm">
                  <i class="far fa-minus-square pr-1"></i>삭제
              </button>-->
              <button class="file-upload btn btn-secondary btn-sm mr-1">
                <i class="fas fa-file-download pr-1"></i>파일업로드
              </button>
              <button class="btn btn-secondary btn-sm">
                <i class="fas fa-file-export pr-1"></i>파일다운로드
              </button>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-4">
            <div class="card card-primary card-outline card-tabs h-100">
              <div class="card-header">
                <h3 class="card-title"></h3>
              </div>
              <div class="card-body">
                <div class="card-body table-responsive p-0 table-hover" style="height: 500px;">
                  <form>
                    <div class="form-group">
                      <DepartmentJstree ref="departmentJstree"
                                        @changedDepartmentActive="handleChangedDepartmentActive">
                      </DepartmentJstree>
                    </div>
                  </form>

                </div>
              </div>
              <div class="card-footer clearfix">
              </div>
            </div><!-- /.card -->
          </div>
          <div class="col-lg-8">
            <div class="card card-primary card-outline card-tabs h-100">
              <div class="card-header p-0 pt-1 border-bottom-0 ">
                <ul class="nav nav-tabs" id="custom-tabs-three-tab" role="tablist">
                  <li class="nav-item">
                    <a class="nav-link active" id="custom-tabs-three-dept-tab" data-toggle="pill" href="#custom-tabs-three-dept" role="tab" aria-controls="custom-tabs-three-dept" aria-selected="true">부서 관리</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" id="custom-tabs-three-member-tab" data-toggle="pill" href="#custom-tabs-three-member" role="tab" aria-controls="custom-tabs-three-member" aria-selected="false">계정 관리</a>
                  </li>
                </ul>
              </div>
              <div class="card-body" style="height: 500px;">
                <div class="tab-content" id="custom-tabs-three-tabContent">
                  <div class="tab-pane fade active show" id="custom-tabs-three-dept" role="tabpanel" aria-labelledby="custom-tabs-three-dept-tab">
                    <DepartmentEditor ref="departmentEditor" :p-department="activeDepartment" @updateData="handleUpdateData"></DepartmentEditor>
                  </div>
                  <div class="tab-pane fade" id="custom-tabs-three-member" role="tabpanel" aria-labelledby="custom-tabs-three-member-tab">
                    <form>
                      <div class="form-row">
                        <div class="col-3">
                          <div class="form-group">
                            <label for="test1">직급</label>
                            <select class="form-control " style="width: 100%;" id="test1">
                              <option>해당없음</option>
                              <option>사원</option>
                              <option>대리</option>
                              <option>과장</option>
                              <option>차장</option>
                              <option>부장</option>
                            </select>
                          </div>
                        </div>
                        <div class="col-3">
                          <div class="form-group">
                            <label for="test2">권한</label>
                            <select class="form-control " style="width: 100%;" id="test2">
                              <option>해당없음</option>
                              <option>관리자</option>
                              <option>자산담당자</option>
                              <option>일반사용자</option>
                              <option>책임사용자</option>
                            </select>
                          </div>
                        </div>
                        <div class="col-3">
                          <div class="form-group">
                            <label for="test3">부서</label>
                            <select class="form-control " style="width: 100%;" id="test3">
                              <option>해당없음</option>
                              <option>보안연구소</option>
                              <option>경영팀</option>
                              <option>보안팀</option>
                            </select>
                          </div>
                        </div>
                        <div class="col-3">
                          <div class="form-group">
                            <label for="test8">사용유무</label>
                            <select class="form-control " style="width: 100%;" id="test8">
                              <option>해당없음</option>
                              <option>비활성화</option>
                              <option>활성화</option>
                            </select>
                          </div>
                        </div>
                      </div>
                    </form>
                    <div class="form-group">
                      <div class="input-group">
                        <input type="search" class="form-control " placeholder="Type your keywords here" value="키워드">
                        <div class="input-group-append">
                          <button type="submit" class="btn btn-primary">
                            <i class="fa fa-search"></i>
                          </button>
                        </div>
                      </div>
                    </div>
                    <div class="card-body table-responsive p-0 table-hover">
                      <table class="table table-head-fixed text-nowrap">
                        <thead>
                        <tr>
                          <th>#</th>
                          <!--                                            <th><input type="checkbox"></th>-->
                          <th>이름</th>
                          <th>이메일</th>
                          <th>생년월일</th>
                          <th>직급</th>
                          <th>권한</th>
                          <th>부서</th>
                          <th>전화번호</th>
                          <th>비고</th>
                          <th>사용유무</th>
                          <th>등록자</th>
                          <th>등록일자</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                          <td>1</td>
                          <!--                                            <td><input type="checkbox"></td>-->
                          <td><a href="#" class="a-click text-dark">최영우</a></td>
                          <td>duddn2197@cmtinfo.co.kr</td>
                          <td>1992-08-11</td>
                          <td>부장</td>
                          <td>관리자</td>
                          <td>보안연구소</td>
                          <td>010-2032-2197</td>
                          <td></td>
                          <td>Y</td>
                          <td>최영우</td>
                          <td>2021-06-25</td>
                        </tr>
                        <tr>
                          <td>2</td>
                          <!--                                            <td><input type="checkbox"></td>-->
                          <td><a href="#" class="a-click text-dark">은선영</a></td>
                          <td>skbb.p767@sk.com</td>
                          <td>1990-02-11</td>
                          <td>대리</td>
                          <td>자산담당자</td>
                          <td>보안연구소</td>
                          <td>010-1123-2435</td>
                          <td></td>
                          <td>Y</td>
                          <td>최영우</td>
                          <td>2021-06-25</td>
                        </tr>
                        <tr>
                          <td>3</td>
                          <!--                                            <td><input type="checkbox"></td>-->
                          <td><a href="#" class="a-click text-dark">박지은</a></td>
                          <td>wldms1431@sk.com</td>
                          <td>1988-02-11</td>
                          <td>과장</td>
                          <td>자산담당자</td>
                          <td>경영팀</td>
                          <td>010-6598-2174</td>
                          <td></td>
                          <td>Y</td>
                          <td>최영우</td>
                          <td>2021-06-25</td>
                        </tr>
                        <tr>
                          <td>4</td>
                          <!--                                            <td><input type="checkbox"></td>-->
                          <td><a href="#" class="a-click text-dark">이새은</a></td>
                          <td>saeeun.lee@sk.com</td>
                          <td>1984-10-30</td>
                          <td>사원</td>
                          <td>일반사용자</td>
                          <td>보안연구소</td>
                          <td>010-6598-2174</td>
                          <td></td>
                          <td>Y</td>
                          <td>최영우</td>
                          <td>2021-06-25</td>
                        </tr>
                        <tr>
                          <td>5</td>
                          <!--                                            <td><input type="checkbox"></td>-->
                          <td><a href="#" class="a-click text-dark">이치은</a></td>
                          <td>terry.lee@sk.com</td>
                          <td>1984-09-20</td>
                          <td>차장</td>
                          <td>책임사용자</td>
                          <td>보안연구소</td>
                          <td>010-3817-4854</td>
                          <td></td>
                          <td>Y</td>
                          <td>최영우</td>
                          <td>2021-06-25</td>
                        </tr>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
              <div class="card-footer clearfix">
                <h4 class="card-title">Showing 1 to 4 of 12 entries</h4>
                <ul class="pagination pagination-sm m-0 float-right">
                  <li class="page-item"><a class="page-link" href="#">&laquo;</a></li>
                  <li class="page-item"><a class="page-link" href="#">1</a></li>
                  <li class="page-item"><a class="page-link" href="#">2</a></li>
                  <li class="page-item"><a class="page-link" href="#">3</a></li>
                  <li class="page-item"><a class="page-link" href="#">&raquo;</a></li>
                </ul>
              </div>
              <!-- /.card -->
            </div>
          </div>
        </div>
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content -->

    <!-- 모달 컴포넌트  -->
<!--    <AuthorityCreate ref="authorityCreate" @updateData="handleUpdateData" style="display: none"></AuthorityCreate>
    <AuthorityUpdate ref="authorityUpdate" @updateData="handleUpdateData" style="display: none"></AuthorityUpdate>-->
    <!-- /모달 컴포넌트  -->
  </div>
  <!-- /.content-wrapper -->
 </template>

<script>
// import $ from "jquery";
import DepartmentJstree from "@/components/department/DepartmentJstree.vue";
import DepartmentEditor from "@/components/department/DepartmentEditor";
export default {
  name: "Organization",
  components: {
    DepartmentJstree,
    DepartmentEditor,
  },
  data() {
    return {
      activeDepartment : null,
    }
  },
  mounted() {
    document.body.classList.remove('login-page');
    document.body.classList.add('layout-top-nav');
  },
  methods: {
    handleChangedDepartmentActive(data) {
      this.activeDepartment = data;
    },
    handleUpdateData() {
      this.$refs.departmentJstree.getJstreeData();
    }
  }
}
</script>

<style scoped>

</style>