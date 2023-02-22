<template>
    <div>
        <button @click="getPosts">Show Posts</button>
    </div>
</template>

<script>

export default {
    methods: {
        getPosts() {
            fetch('https://jsonplaceholder.typicode.com/todos/1')
            .then(response => response.json())
            .then(json => console.log(json))
        }
    }
}
</script>