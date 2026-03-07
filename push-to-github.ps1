$repoUrl = Read-Host "Enter your GitHub repository URL"
if ([string]::IsNullOrWhiteSpace($repoUrl)) {
    Write-Host "No URL entered. Exiting."
    exit 1
}

git remote remove origin 2>$null

git remote add origin $repoUrl
git push -u origin main
