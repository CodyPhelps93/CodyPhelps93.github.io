---
title: Cody Phelps Portfolio
---

<h1> Blog Posts</h1>
<ul>
  {% for post in site.posts %}
  <li>
    <a href="{{ post.url }}">{{ post.title }}</a>
    <p>{{ post.excerpt}}</p>
  </li>
  {% endfor %}
</ul>
