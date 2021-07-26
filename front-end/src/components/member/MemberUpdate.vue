<template>
  <!-- Modal -->
  <div class="modal fade" id="member-update-modal" tabindex="-1" aria-labelledby="authority-create-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header ">
          <h2 class="tit_step">회원수정</h2>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form>
            <div class="form-group">
              <label for="name">권한명<span class="text-danger">*</span></label>
              <input type="text" class="form-control" id="name" placeholder="">
            </div>
            <div class="form-group">
              <label for="code">권한코드<span class="text-danger">*</span></label>
              <div class="form-group row">
                <div class="col-sm-10">
                  <input type="text" class="form-control " id="code" placeholder="">
                </div>
                <div class="col-sm">
                  <input type="button" class="form-control btn btn-primary btn-sm" value="중복체크" @click="clickCodeCheckBtn">
                </div>
              </div>
            </div>
            <div class="form-group">
              <label for="remark">비고<code></code></label>
              <textarea class="form-control" id="remark"></textarea>
            </div>
            <div class="form-group">
              <div class="custom-control custom-radio d-inline pr-1">
                <input class="custom-control-input" type="radio" id="useYnFalse" name="useYnRadio" @click="clickRadioBtn(false)">
                <label for="useYnFalse" class="custom-control-label">비활성화</label>
              </div>
              <div class="custom-control custom-radio d-inline">
                <input class="custom-control-input" type="radio" id="useYnTrue" name="useYnRadio" checked="" @click="clickRadioBtn(true)">
                <label for="useYnTrue" class="custom-control-label">활성화</label>
              </div>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">  <i class="far fa-window-close pr-1"></i>취소</button>
          <button id="createBtn" type="button" class="btn btn-secondary" @click="createBtnClick"> <i class="far fa-edit pr-1"></i>등록</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "MemberUpdate"
}
</script>

<style scoped>

</style>