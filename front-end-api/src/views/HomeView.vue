<script setup>
import { onMounted, ref } from 'vue';
import axios from "axios";
const API_URL = "http://localhost:8080/api/v1.0/photos"
const photos = ref(null);
function getAllPhotos() {
  axios.get(API_URL)
    .then(res => {
      const data = res.data;
      photos.value = data.filter(photo => photo.visibile);
    })
    .catch(err => console.log(err));
}
onMounted(() => {
  getAllPhotos();
});
</script>

<template>
  <main>
    <div class="container">

      <h1>Foto</h1>

      <div class="py-3">
        <div class="card-deck d-flex flex-wrap gap-5">
          <div class="card" style="width: 250px; height: 425px;" v-for="photo in photos" :key="photo.id">
            <div style="height: 150px;">
              <img class="card-img-top" :src="photo.url" :alt="photo.titolo"
                style="width: 100%; height: 100%;object-fit: cover;" />
            </div>
            <div class="card-body" style="height: 275px;">
              <h4 class="card-title text-center">{{ photo.titolo }}</h4>
              <p class="card-text">{{ photo.descrizione }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>

  </main>
</template>
