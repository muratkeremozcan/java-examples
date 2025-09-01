#!/bin/bash
# Setup script for Git hooks
# Run this script to configure Git hooks for this project

echo "🔧 Setting up Git hooks for Java project..."

# Make sure we're in a git repository
if [ ! -d ".git" ]; then
    echo "❌ Error: This directory is not a Git repository."
    echo "Please run this script from the root of your Git repository."
    exit 1
fi

# Configure Git to use our custom hooks directory
git config core.hooksPath .githooks

# Make hooks executable
chmod +x .githooks/pre-commit

echo "✅ Git hooks configured successfully!"
echo ""
echo "📋 What happens now:"
echo "   • Every time you commit, 'make fix' will run automatically"
echo "   • Then 'make precommit' will validate everything"
echo "   • If any checks fail, the commit will be blocked"
echo ""
echo "🧪 Test it: Try making a commit to see the hooks in action!"
echo "💡 To skip hooks (emergency only): git commit --no-verify"
