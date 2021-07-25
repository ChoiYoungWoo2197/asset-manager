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
            <div v-if="activeVueComponent !== 'department'">
              <button class="create btn btn-secondary btn-sm mr-1">
                <i class="far fa-plus-square pr-1"></i>등록
              </button>
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
                    <a class="nav-link active" id="custom-tabs-three-dept-tab" data-toggle="pill" href="#custom-tabs-three-dept"
                       role="tab" aria-controls="custom-tabs-three-dept" aria-selected="true" @click="clickTab('department')">부서 관리</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" id="custom-tabs-three-member-tab" data-toggle="pill" href="#custom-tabs-three-member"
                       role="tab" aria-controls="custom-tabs-three-member" aria-selected="false" @click="clickTab('member')">계정 관리</a>
                  </li>
                </ul>
              </div>
              <div class="card-body" style="height: 500px;">
                <div class="tab-content" id="custom-tabs-three-tabContent">
                  <div class="tab-pane fade active show" id="custom-tabs-three-dept" role="tabpanel" aria-labelledby="custom-tabs-three-dept-tab">
                    <DepartmentEditor ref="departmentEditor" :p-department="activeDepartment" @updateData="handleUpdateData"></DepartmentEditor>
                  </div>
                  <div class="tab-pane fade" id="custom-tabs-three-member" role="tabpanel" aria-labelledby="custom-tabs-three-member-tab">
                    <Member ref="member" :p-department="activeDepartment"></Member>
                  </div>
                </div>
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
import Member from "@/components/member/Member";
export default {
  name: "Organization",
  components: {
    DepartmentJstree,
    DepartmentEditor,
    Member,
  },
  data() {
    return {
      activeVueComponent : 'department',
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
      this.activeDepartment = null;
      this.$refs.departmentJstree.getJstreeData();
    },
    clickTab(activeVueComponent) {
      this.activeVueComponent = activeVueComponent;
    }
  }
}
</script>

<style scoped>

</style>