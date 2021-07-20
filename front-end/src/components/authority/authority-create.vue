<template>
  <!-- Modal -->
  <div class="modal fade" id="authority-create-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header ">
          <!--                        <h5 class="modal-title" id="exampleModalLabel">권한등록</h5>-->
          <h2 class="tit_step">권한등록</h2>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form>
            <div class="form-group">
              <label for="label1">권한명<span class="text-danger">*</span></label>
              <input type="text" class="form-control" id="label1" placeholder="">
            </div>
            <div class="form-group">
              <label for="label2">권한코드<span class="text-danger">*</span></label>
              <div class="form-group row">
                <div class="col-sm-10">
                  <input type="text" class="form-control " id="label2" placeholder="">
                </div>
                <div class="col-sm">
                  <input type="button" class="form-control btn btn-primary btn-sm" value="중복체크">
                </div>
              </div>
            </div>
            <div class="form-group">
              <label for="label3">비고<code></code></label>
              <textarea class="form-control" id="label3"></textarea>
            </div>
            <div class="form-group">
              <div class="custom-control custom-radio d-inline pr-1">
                <input class="custom-control-input" type="radio" id="customRadio1" name="customRadio">
                <label for="customRadio1" class="custom-control-label">비활성화</label>
              </div>
              <div class="custom-control custom-radio d-inline">
                <input class="custom-control-input" type="radio" id="customRadio2" name="customRadio" checked="">
                <label for="customRadio2" class="custom-control-label">활성화</label>
              </div>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">  <i class="far fa-window-close pr-1"></i>취소</button>
          <button type="button" class="btn btn-primary "> <i class="far fa-edit pr-1"></i>등록</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "authority-create"
}
</script>

<style scoped>

</style>