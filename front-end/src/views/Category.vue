<template>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h4 class="m-0"> 카테고리 정보관리 <small></small></h4>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">기초정보</a></li>
              <li class="breadcrumb-item active">카테고리 정보관리</li>
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
            <div v-if="activeVueComponent === 'category'">
<!--              <button class="file-upload btn btn-secondary btn-sm mr-1">
                <i class="far fa-plus-square pr-1"></i>등록
              </button>-->
            </div>
            <div v-else>
              <button class="create btn btn-secondary btn-sm mr-1" @click="showCreateModel">
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
                      <CategoryJstree ref="categoryJstree"
                                        @changedCategoryActive="handleChangedCategoryActive">
                      </CategoryJstree>
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
                    <a class="nav-link active" id="custom-tabs-three-category-tab" data-toggle="pill" href="#custom-tabs-three-category"
                       role="tab" aria-controls="custom-tabs-three-category" aria-selected="true" @click="clickTab('category')">카테고리 관리</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" id="custom-tabs-three-category-specification-tab" data-toggle="pill" href="#custom-tabs-three-category-specification"
                       role="tab" aria-controls="custom-tabs-three-custom-tabs-three-category-specification" aria-selected="false" @click="clickTab('spcs')">규격 관리</a>
                  </li>
                </ul>
              </div>
              <div class="card-body" style="height: 500px;">
                <div class="tab-content" id="custom-tabs-three-tabContent">
                  <div class="tab-pane fade active show" id="custom-tabs-three-category" role="tabpanel" aria-labelledby="custom-tabs-three-category-tab">
                    <CategoryEditor ref="categoryEditor" :p-category="activeCategory" @updateData="handleUpdateData"></CategoryEditor>
                  </div>
                  <div class="tab-pane fade" id="custom-tabs-three-category-specification" role="tabpanel" aria-labelledby="custom-tabs-three-category-specification-tab">
                    <CategorySpecification ref="categorySpecification" :p-category="activeCategory"></CategorySpecification>
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


  </div>
  <!-- /.content-wrapper -->
</template>
<script>
import CategoryJstree from "@/components/category/CategoryJstree.vue";
import CategoryEditor from "@/components/category/CategoryEditor.vue";
import CategorySpecification from "@/components/category/CategorySpecification.vue";
export default {
  name: "Category",
  components: {
    CategoryJstree,
    CategoryEditor,
    CategorySpecification
  },
  data() {
    return {
      activeVueComponent : 'category',
      activeCategory : null,
    }
  },
  mounted() {
    document.body.classList.remove('login-page');
    document.body.classList.add('layout-top-nav');
  },
  methods: {
    handleChangedCategoryActive(data) {
      this.activeCategory = data;
    },
    handleUpdateData() {
      this.activeCategory = null;
      this.$refs.categoryJstree.getJstreeData();
    },
    clickTab(activeVueComponent) {
      this.activeVueComponent = activeVueComponent;
    },
    showCreateModel() {
      // this.$refs.member.showCreateModal();
    }
  }
}
</script>

<style scoped>

</style>