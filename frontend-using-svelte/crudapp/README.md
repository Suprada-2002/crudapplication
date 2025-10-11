## Technologies
1. Package Manager: bun
2. Styling: Tailwind CSS

## Setup For Frontend
1. Create a Sveltekit project
```
npx sv create projectname
bun install -D tailwindcss postcss autoprefixer
```

2. Setup tailwnd css
```
npm install tailwindcss @tailwindcss/vite
add in vite.config.js:
    import tailwindcss from '@tailwindcss/vite';
    plugins: [
    tailwindcss(),
    sveltekit(),
  ],
in layout.css
    @import "tailwindcss";
```

## Files:
1. client.ts : This ensures every call is type-safe (Promise<T>), and you get clear error objects for easy handling in your UI.
2. 