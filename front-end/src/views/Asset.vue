<template>
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h4 class="m-0"> 자산정보관리 <small></small></h4>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">자산관리</a></li>
              <li class="breadcrumb-item active">자산정보관리</li>
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
              <button class="create btn btn-secondary btn-sm mr-1" @click="showCreateModal">
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
          <div class="col-lg">
            <div class="card card-primary card-outline">
              <div class="card-header">
                <div class="col-lg">
                  <form>
                    <div class="form-row">
                      <div class="col-3">
                        <div class="form-group">
                          <label for="test1">구분</label>
                          <select class="form-control " style="width: 100%;" id="test1">
                            <option>해당없음</option>
                            <option>구매</option>
                            <option>렌탈</option>
                          </select>
                        </div>
                        <div class="form-group">
                          <label for="test5">렌탈계약기간:</label>
                          <div class="input-group date " id="test5" data-target-input="nearest">
                            <input type="text" class="form-control datetimepicker-input " data-target="#test5" placeholder="렌탈계약기간"/>
                            <div class="input-group-append" data-target="#test5" data-toggle="datetimepicker">
                              <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="col-3">
                        <div class="form-group">
                          <label for="test2">카테고리</label>
                          <select class="form-control " style="width: 100%;" id="test2">
                            <option>해당없음</option>
                            <option>컴퓨터</option>
                            <option>데스크탑</option>
                            <option>CPU</option>
                            <option>모니터</option>
                          </select>
                        </div>
                        <div class="form-group">
                          <label for="test6">담당자</label>
                          <select class="form-control " style="width: 100%;" id="test6">
                            <option>해당없음</option>
                            <option>최영우</option>
                            <option>은선영</option>
                            <option>박지은</option>
                            <option>이새은</option>
                            <option>이치은</option>
                          </select>
                        </div>
                      </div>
                      <div class="col-3">
                        <div class="form-group">
                          <label for="test3" >구매일자</label>
                          <div class="input-group date " id="test3" data-target-input="nearest">
                            <input type="text" class="form-control datetimepicker-input " data-target="#test3" placeholder="구매일자"/>
                            <div class="input-group-append" data-target="#test3" data-toggle="datetimepicker">
                              <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                            </div>
                          </div>
                        </div>
                        <div class="form-group">
                          <label for="test7">부서</label>
                          <select class="form-control " style="width: 100%;" id="test7">
                            <option>해당없음</option>
                            <option>보안연구소</option>
                            <option>경영팀</option>
                            <option>보안팀</option>
                          </select>
                        </div>
                      </div>
                      <div class="col-3">
                        <div class="form-group">
                          <label for="test4">수령일자</label>
                          <div class="input-group date " id="test4" data-target-input="nearest">
                            <input type="text" class="form-control datetimepicker-input " data-target="#test4" placeholder="구매일자"/>
                            <div class="input-group-append" data-target="#test4" data-toggle="datetimepicker">
                              <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                            </div>
                          </div>
                        </div>
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
                      <input type="search" class="form-control " placeholder="자산명" value="">
                      <div class="input-group-append">
                        <button type="submit" class="btn btn-primary">
                          <i class="fa fa-search"></i>
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="card-body">
                <div class="card-body table-responsive p-0 table-hover" style="height: 500px;">
                  <table class="table table-head-fixed text-nowrap">
                    <thead>
                    <tr>
                      <th>#</th>
                      <!--                                            <th><input type="checkbox"></th>-->
                      <th>자산명</th>
                      <th>자산코드</th>
                      <th>카테고리명</th>
                      <th >수량</th>
                      <th>가격</th>
                      <th>구매일자</th>
                      <th>수령일자</th>
                      <!--                                            <th>모델명</th>
                                                                  <th>시리얼넘버</th>-->
                      <th>담당자</th>
                      <th>담당부서</th>
                      <th>구분</th>
                      <th>업체명</th>
                      <th>계약시작일자</th>
                      <th>계약종료일자</th>
                      <th>재고</th>
                      <th>비고</th>
                      <th>사용유무</th>
                      <th>등록자</th>
                      <th>등록일자</th>
                      <th>렌탈내역</th>
                      <th>변경내역</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                      <td>1</td>
                      <!--                                            <td><input type="checkbox"></td>-->
                      <td><a href="#" class="a-click text-dark">모니터</a></td>
                      <td>asset-1</td>
                      <td>monitor</td>
                      <td>1</td>
                      <td>50000</td>
                      <td>2021-05-21</td>
                      <td>2021-06-05</td>
                      <!--                                            <td>Dell U2415</td>
                                                                  <td>CN02JVJ4QDC00879176U</td>-->
                      <td>최영우</td>
                      <td>보안연구소</td>
                      <td>렌탈</td>
                      <td>롯데렌탈</td>
                      <td>2021-06-01</td>
                      <td>2022-06-01</td>
                      <td>1</td>
                      <td></td>
                      <td>Y</td>
                      <td>최영우</td>
                      <td>2021-06-08</td>
                      <td>
                        <button class="asset-extention btn btn-info btn-sm">렌탈내역</button>
                      </td>
                      <td>
                        <button class=" btn btn-info btn-sm">변경내역</button>
                      </td>
                    </tr>
                    </tbody>
                  </table>
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
            </div><!-- /.card -->
          </div>
        </div>
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content -->


    <!--  모달 컴포넌트  -->
    <AssetCreate ref="assetCreate" @updateData="handleUpdateData" style="display : none"></AssetCreate>
    <AssetUpdate ref="assetUpdate" @updateData="handleUpdateData" style="display : none"></AssetUpdate>

    <!--  /모달 컴포넌트  -->

  </div>
</template>

<script>
import AssetCreate from "@/components/asset/AssetCreate.vue";
import AssetUpdate from "@/components/asset/AssetUpdate.vue";
import $ from "jquery";

export default {
  name: "Asset",
  components: {
    AssetCreate,
    AssetUpdate,
  },
  data() {
    return {

    }
  },
  mounted() {
    document.body.classList.remove('login-page');
    document.body.classList.add('layout-top-nav');
  },
  methods: {
    handleUpdateData(data) {
      console.log(data);
    },
    showCreateModal() {
      this.$refs.assetCreate.clearData();
      $('#asset-create-modal').modal("show");
    }
  }
}
</script>

<style scoped>

</style>