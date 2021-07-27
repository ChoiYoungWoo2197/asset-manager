<template>
  <!-- Modal -->
  <div class="modal fade" id="category-specification-create-modal" tabindex="-1" aria-labelledby="authority-create-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header ">
          <h2 class="tit_step">카테고리규격 등록</h2>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div class="row">
            <div class="col-lg">
              <div class="card card-primary card-outline">
                <div class="card-body">
                  <form>
                    <div class="form-group">
                      <label for="categoryName">카테고리명<span class="text-danger"></span></label>
                      <input type="text" class="form-control " id="categoryName" placeholder="" disabled readonly="readonly">
                    </div>
                    <div class="form-group">
                      <label for="name">규격명<span class="text-danger">*</span></label>
                      <input type="text" class="form-control " id="name" placeholder="" @keydown="keyDownNameInput">
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
              </div>
            </div>
          </div>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">  <i class="far fa-window-close pr-1"></i>취소</button>
          <button id="createBtn" type="button" class="btn btn-primary" @click="createBtnClick"> <i class="far fa-edit pr-1"></i>등록</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import axios from "axios";

export default {
  name: "CategorySpecificationCreate",
  props: {
    pCategory : Object,
  },
  data() {
    return {
      category : null,
      useYn : true,
    }
  },
  computed: {
  },
  watch: {
    pCategory() {
      this.category = this.pCategory;
      this.findCategory();
    }
  },
  methods: {
    clearData() {
      $( '#category-specification-create-modal input#name' ).val("");
      // $( '#category-specification-create-modal input#categoryName' ).val("");
      // $('#category-specification-create-modal button#createBtn').removeClass('btn-primary');
      // $('#category-specification-create-modal button#createBtn').addClass('btn-secondary');
      this.useYn = true;
    },
    findCategory() {
      const vm = this;
      if(vm.category === null) return false;

      axios.get('http://localhost:8080/api/categorys/' + vm.category.id, {
      }).then(response => {
        if(response.status === 200) {
          // console.log(response)
          $( '#category-specification-create-modal input#categoryName' ).val(response.data.name);
        }
      }).catch(e => {
        alert(e);
      })
    },
    clickRadioBtn(flag){
      this.useYn = flag;
    },
    createBtnClick() {
      const vm = this;
      if($( '#category-specification-create-modal input#name' ).val() ===""
          || $( '#category-specification-create-modal input#categoryName' ).val() === "") {
        if($( '#category-specification-create-modal input#categoryName' ).val() === "") {
          alert('카테고리를 생성 및 선택해주세요.');
          return  false;
        } else {
          alert("규격명을 입력해 주세요.");
          return false;
        }
      }

      axios.post('http://localhost:8080/api/category-specifications', {
        name : $( '#category-specification-create-modal input#name').val(),
        parentId : vm.category.id,
        register : "1",
        useYn : this.useYn,
      }).then(response => {
        // console.log(response);
        if(response.status === 200) {
          vm.$emit("updateData", response.data);
          $('#category-specification-create-modal').modal("hide");
        }
      }).catch(e => {
        alert(e);
      })
    },
    keyDownNameInput() {

    }
  }
}
</script>

<style scoped>

</style>