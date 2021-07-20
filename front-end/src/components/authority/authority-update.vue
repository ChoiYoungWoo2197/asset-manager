<template>
  <!-- Modal 수정 -->
  <div class="modal fade" id="authority-update-modal" tabindex="-1" aria-labelledby="exampleUpdateModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg modal-dialog-scrollable">
      <div class="modal-content ">
        <div class="modal-header">
          <!--                        <h5 class="modal-title" id="exampleUpdateModalLabel">권한수정</h5>-->
          <h2 class="tit_step">권한수정</h2>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form>
            <div class="form-group">
              <label for="label1u">권한명<span class="text-danger">*</span></label>
              <input type="text" class="form-control " id="label1u" placeholder="" value="관리자">
            </div>
            <div class="form-group">
              <label for="label2u">권한코드<code></code></label>
              <input type="text" class="form-control " id="label2u" placeholder="" value="admin" readonly="readonly">
            </div>
            <div class="form-group">
              <label for="label3u">비고<code></code></label>
              <textarea class="form-control" id="label3u"></textarea>
            </div>
            <div class="form-group">
              <div class="custom-control custom-radio d-inline pr-1">
                <input class="custom-control-input" type="radio" id="customRadio1u" name="customRadio">
                <label for="customRadio1u" class="custom-control-label">비활성화</label>
              </div>
              <div class="custom-control custom-radio d-inline">
                <input class="custom-control-input" type="radio" id="customRadio2u" name="customRadio" checked="">
                <label for="customRadio2u" class="custom-control-label">활성화</label>
              </div>
            </div>
            <!--                            <div class="form-group">
                                            <label for="label5u">등록자<code></code></label>
                                            <input type="text" class="form-control " id="label5u" placeholder="" value="최영우" readonly="readonly">
                                        </div>
                                        <div class="form-group">
                                            <label for="label6u">등록일자<code></code></label>
                                            <input type="text" class="form-control " id="label6u" placeholder="" value="2021-06-25" readonly="readonly">
                                        </div>-->
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">  <i class="far fa-window-close pr-1"></i>취소</button>
          <button type="button" class="btn btn-primary"> <i class="far fa-edit pr-1"></i>수정</button>
          <button type="button" class="btn btn-danger"><i class="far fa-trash-alt pr-1"></i>삭제</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "authority-update"
}
</script>

<style scoped>

</style>