public static void heapSort(int[] array) {
    int n = array.length;

    for (int i = (n / 2) - 1; i >= 0; i--) {
        heapify(array, n, i);
    }
    
    for (int i = n - 1; i > 0; i--) {
        swap(array, 0, i); // 루트 노드와 최하위 노드 교체
        heapify(array, i, 0); // 최하위 노드에서 루트 노드까지 상향적으로 비교
    }
}

public static void heapify(int array[], int n, int i) {
    int p = i; // 부모 위치

    int l = i * 2 + 1; // 왼쪽 자식
    int r = i * 2 + 2; // 오른쪽 자식

    // 왼쪽 자식노드가 부모보다 클 경우
    if (l < n && array[p] < array[l]) {
        p = l;
    }

    // 오른쪽 자식노드가 부모보다 클 경우
    if (r < n && array[p] < array[r]) {
        p = r;
    }

    // 부모노드 < 자식노드 : 교체 후 다시 하단 부분 이어서 heapify
    if (i != p) {
        swap(array, p, i);
        heapify(array, n, p); // 교체가 일어났으면 하단 트리도 이어서 교체
    }
}

private static void swap(int[] array, int p, int i) {
    int tmp = array[p];
    array[p] = array[i];
    array[i] = tmp;
}
